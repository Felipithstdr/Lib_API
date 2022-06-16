package br.com.mbs.batch.item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import br.com.mbs.entidades.Books;

public class BookWriter implements ItemWriter<Books>{

	String fileName = "datas.txt";

	@Override
	public void write(List<? extends Books> items) throws Exception {
		System.out.println("Write");
		
		for(Books book : items) {
			String conteudo = book.getTitle() + "," + book.getQuantity();
			FileWriter fw = new FileWriter(fileName, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(conteudo);
		    bw.newLine();
		    bw.close();
		}	
		
	}

}
