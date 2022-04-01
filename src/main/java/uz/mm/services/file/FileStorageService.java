package uz.mm.services.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.mm.dto.file.FileStorageCreateDto;
import uz.mm.dto.file.FileStorageDto;
import uz.mm.dto.file.FileStorageUpdateDto;
import uz.mm.entity.file.FileStorage;
import uz.mm.exeptions.fileStore.FileStorageException;
import uz.mm.exeptions.fileStore.StorageFileNotFoundException;
import uz.mm.mappers.file.FileStorageMapper;
import uz.mm.properties.FileStorageProperties;
import uz.mm.repositories.file.FileStorageRepository;
import uz.mm.services.base.AbstractService;
import uz.mm.services.base.GenericCrudService;
import uz.mm.validators.FileStorageValidator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;


@Service
public class FileStorageService extends AbstractService<
        FileStorageRepository,
        FileStorageMapper,
        FileStorageValidator>
        implements GenericCrudService<
        FileStorageDto,
        FileStorageCreateDto,
        FileStorageUpdateDto,
            String> {

    private final Path rootLocation;

    protected FileStorageService(FileStorageMapper mapper, FileStorageValidator validator, FileStorageRepository repository, FileStorageProperties properties) {
        super(mapper, validator, repository);
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String create(FileStorageCreateDto createDto) {

        FileStorage fileStorage = mapper.fromCreateDto(createDto);
        fileStorage = uploadAnyFile(createDto.getFile(), fileStorage);
        fileStorage.setId(UUID.randomUUID().toString().replace("-", ""));
        repository.save(fileStorage);

        return fileStorage.getId();
    }

    @Override
    public void delete(String id) {
        Optional<FileStorage> fileStorageOptional = repository.findByIdAndDeletedFalse(id);
        if (fileStorageOptional.isPresent()) {
            FileStorage fileStorage = fileStorageOptional.get();
            fileStorage.setDeleted(true);
            repository.save(fileStorage);
        }
    }

    @Override
    public void update(FileStorageUpdateDto updateDto) {

    }

    @Override
    public FileStorageDto get(String id) {

        Optional<FileStorage> fileStorageOptional = repository.findByIdAndDeletedFalse(id);
        if (fileStorageOptional.isPresent()) {
            FileStorage fileStorage = fileStorageOptional.get();
            return mapper.toDto(fileStorage);
        } else {
            throw new StorageFileNotFoundException("file not found");
        }
    }

    public FileStorageDto getAsResource(String id) {
        Optional<FileStorage> fileStorageOptional = repository.findByIdAndDeletedFalse(id);
        Resource resource = fileStorageOptional.map(fileStorage -> loadAsResource(fileStorage.getGeneratedName())).orElse(null);
        FileStorageDto fileStorageDto = mapper.toDto(fileStorageOptional.get());
        fileStorageDto.setFile(resource);

        return fileStorageDto;

    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }


    @Override
    public List<FileStorageDto> getAll() {
        List<FileStorage> fileStorages = repository.findAll();
        return mapper.toDto(fileStorages);
    }

    public String uploadVideoFile(MultipartFile multipartFile, String lessonId) {

        validator.checkVideoFile(multipartFile);
        FileStorage fileStorage = uploadAnyFile(multipartFile, new FileStorage());
        fileStorage.setId(UUID.randomUUID().toString().replace("-", ""));
        fileStorage.setLessonId(lessonId);
        repository.save(fileStorage);

        return fileStorage.getPath();

    }

    public FileStorage uploadAnyFile(MultipartFile file, FileStorage fileStorage) {

        String format = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String generatedName = UUID.randomUUID().toString().replace("-", "") + "." + format;
        Path path = Paths.get(rootLocation.toString(), generatedName);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileStorage.setGeneratedName(generatedName);
        fileStorage.setOriginalName(file.getOriginalFilename());
        fileStorage.setType(file.getContentType());
        fileStorage.setPath(path.toString());

        return fileStorage;
    }


    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new FileStorageException("Failed to read stored files", e);
        }
    }


    public Stream<Resource> loadAllAsResource() {
        List<Resource> resources = new ArrayList<>();
        Stream<Path> pathStream = loadAll();
        pathStream.forEach(path -> {
            try {
                Resource resource = new UrlResource(path.toUri());
                resources.add(resource);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });

        return resources.stream();

    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new FileStorageException("Could not initialize storage", e);
        }
    }

    public void hardDeleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
        init();
    }
}
