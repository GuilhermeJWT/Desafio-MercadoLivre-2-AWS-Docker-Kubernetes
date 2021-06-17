package br.com.systemsgs.mercadolivre.config;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public static <O, D> D converteEntidade(O origem, Class<D> destino) {
		return mapper.map(origem, destino);
	}

	public static <O, D> List<D> converteList(List<O> origem, Class<D> destino) {
		List<D> destinoEntidade = new ArrayList<D>();

		for(Object object: origem) {
			destinoEntidade.add(mapper.map(object, destino));
		}

		return destinoEntidade;
	} 

}
