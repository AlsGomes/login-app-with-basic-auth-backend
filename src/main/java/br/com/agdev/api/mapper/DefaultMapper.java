package br.com.agdev.api.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;

public abstract class DefaultMapper<E, D> {

	private Class<E> classE;
	private Class<D> classD;

	public DefaultMapper(Class<E> clazzE, Class<D> clazzD) {
		this.classE = clazzE;
		this.classD = clazzD;
	}

	public D toDTO(E entity) {
		try {
			D dto = this.classD.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();

			throw new MapperException("Erro na conversão de uma Entidade para DTO");
		}
	}
	
	public void copyPropertiesToDTO(D dto, E entity) {
		BeanUtils.copyProperties(entity, dto);
	}

	public List<D> toDTOList(Collection<E> entityCollection) {
		return entityCollection.stream().map(this::toDTO).toList();
	}

	public E toEntity(D dto) {
		E entity;
		try {
			entity = this.classE.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(dto, entity);
			return entity;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();

			throw new MapperException("Erro na conversão de um DTO para Entidade");
		}
	}

	public void copyPropertiesToEntity(E entity, D dto) {
		BeanUtils.copyProperties(dto, entity);
	}

	public List<E> toEntityList(Collection<D> dtoCollection) {
		return dtoCollection.stream().map(this::toEntity).toList();
	}

}
