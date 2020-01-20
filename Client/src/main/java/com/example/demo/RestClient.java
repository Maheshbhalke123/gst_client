package com.example.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.client.RestTemplate;

import com.example.model.Gst;
import com.example.model.ProductType;
import com.example.model.Products;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.opencsv.CSVWriter;

public class RestClient {

	RestTemplate restTemplate = new RestTemplate();

	public void requestGetData() throws JsonParseException, JsonMappingException, IOException {

		try {
			String jsonString = restTemplate.getForObject("http://localhost:8080/api/typeadded", String.class);
			System.out.println("Response:: " + jsonString);

			ObjectMapper mapper1 = new ObjectMapper();
			List<ProductType> productList = mapper1.readValue(jsonString, new TypeReference<List<ProductType>>() {
			});
			String filePath = "src/main/resources/product.csv";
			writeDataLineByLine(filePath, productList);

			System.out.println("csv created successfully");
			System.out.println("---------------------------------------");

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	public static void writeDataLineByLine(String filePath, List<ProductType> productList) {

		File file = new File(filePath);
		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile);

		
			String[] header = { "id", "type", "product", "gst_id", "gst_cgstrate", "gst_sgstrate" };
			writer.writeNext(header);
			for (ProductType productType : productList) {
				com.example.model.Gst gst = productType.getGst();
				String[] data1 = { productType.getId().toString(), productType.getType(),
						productType.getProducts().toString(), String.valueOf(gst.getId()),
						String.valueOf(gst.getCgstRate()), String.valueOf(gst.getSgstRate()) };
				writer.writeNext(data1);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -------------------------------send data through
	// CSV--------------------------------

	public void requestPostData() {

		final String input1 = "src/main/resources/productData.csv";

		try (Reader reader = Files.newBufferedReader(Paths.get(input1));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {

			for (CSVRecord csvRecord : csvParser) { // Accessing Values by Column Index

				ProductType productType = new ProductType();
				productType.setId(Integer.parseInt(csvRecord.get(0)));
				productType.setType(csvRecord.get(1));

				List<Products> productList = new ArrayList<>();
				Products products = new Products();
				productList.add(products);
				productType.setProducts(productList);

				Gst gst = new Gst();
				gst.setId(Integer.parseInt(csvRecord.get(3)));
				gst.setCgstRate(Double.parseDouble(csvRecord.get(4)));
				gst.setSgstRate(Double.parseDouble(csvRecord.get(5)));

				productType.setGst(gst);

				Gson gson = new Gson();

				System.out.println("print product type" + gson.toJson(productType));

				String postForObject = restTemplate.postForObject("http://localhost:8080/api/addtype", productType,
						String.class);
				System.out.println("post response :" + postForObject);
				System.out.println("-----------------------------------------------");

			}
		} catch (Exception e) {
			System.out.println("Exception::::" + e);
		}

	}

}
