package com.fraga.bdmg.populate;

import java.util.ArrayList;
import java.util.List;

import com.fraga.bdmg.model.BemImovel;

public class BemImovelPopulate {

	public static List<BemImovel> populate(){
		List<BemImovel> bensImoveis = new ArrayList<>() {
			{
				add(new BemImovel(0L,"Apartamento RJ", 300000.00,0L));
				add(new BemImovel(1L,"Fiat Uno", 15000.00,1L));
				add(new BemImovel(2L,"Casa na Praia", 250000.00));
				add(new BemImovel(3L,"Galpão SP", 900000.00));
				add(new BemImovel(4L,"Lote BH", 220000.00));
				add(new BemImovel(5L,"BMW", 500000.00));
				add(new BemImovel(6L,"Casa Interior", 90000.00));
				add(new BemImovel(7L,"VW Gol", 35000.00));
				add(new BemImovel(8L,"Loja Comercial Juiz de Fora", 365000.00));
			}
		};
		return bensImoveis;
	}
	
}
