package com.fraga.bdmg.populate;

import java.util.ArrayList;
import java.util.List;

import com.fraga.bdmg.data.model.BemImovel;

public class BemImovelPopulate {

	public static List<BemImovel> populate(){
		List<BemImovel> bensImoveis = new ArrayList<>() {
			{
				add(new BemImovel(0L,"Apartamento Copacabana RJ", 1.0,0L));
				add(new BemImovel(1L,"Sítio MG", 2.0,1L));
				add(new BemImovel(2L,"Casa na Praia", 3.0, 2L));
				add(new BemImovel(3L,"Galpão SP", 4.0, 3L));
				add(new BemImovel(4L,"Lote BH", 1.0, 4L));
				add(new BemImovel(5L,"Fazenda GO", 2.0, 5L));
				add(new BemImovel(6L,"Casa Interior", 3.0, 6L));
				add(new BemImovel(7L,"Triplex", 4.0, 7L));
				add(new BemImovel(8L,"Loja Comercial Juiz de Fora", 1.0, 8L));
			}
		};
		return bensImoveis;
	}
	
}
