package uz.mm.mappers.file;

import org.mapstruct.Mapper;
import uz.mm.dto.file.FileStorageCreateDto;
import uz.mm.dto.file.FileStorageDto;
import uz.mm.dto.file.FileStorageUpdateDto;
import uz.mm.entity.file.FileStorage;
import uz.mm.mappers.base.AbstractMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileStorageMapper extends AbstractMapper<
        FileStorage,
        FileStorageDto,
        FileStorageCreateDto,
        FileStorageUpdateDto> {

    @Override
    FileStorageDto toDto(FileStorage entity);

    @Override
    List<FileStorageDto> toDto(List<FileStorage> entities);

    @Override
    FileStorage fromCreateDto(FileStorageCreateDto createDto);

    @Override
    FileStorage fromUpdateDto(FileStorageUpdateDto updateDto);
}
