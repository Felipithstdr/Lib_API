package br.com.mbs.batch.item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import br.com.mbs.entidades.BuyBook;

public class BookWriter implements ItemWriter<BuyBook>{
	String fileName = "datas.txt";
	@Override
	public void write(List<? extends BuyBook> items) throws Exception {
		System.out.println("Write");
		
		for(BuyBook books : items) {
			String conteudo = "Qtd: " + books.getQuantity() + "-" + "ID: " + books.getId();
			FileWriter fw = new FileWriter(fileName, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(conteudo);
		    bw.newLine();
		    bw.close();
		}	
		
	}

}
