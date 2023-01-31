package com.techelevator.data;

import com.techelevator.exceptions.IllegalProductQuantityException;
import com.techelevator.exceptions.IllegalProductCodeException;
import com.techelevator.exceptions.IllegalProductTypeException;
import com.techelevator.interfaces.TypeConstants;
import com.techelevator.models.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class Repo implements TypeConstants {
    private static Map<String, Product> listOfProducts = new TreeMap<>();

    /**
     * @param filePath (String) Relative or Absolute string representation of the input file's storage
     *                 location. Example C:\User\Desktop\input.txt
     * @return boolean (true or false) that indicates whether the file at the filePath location was
     *                  read successfully
     */

    public static boolean startup(String filePath) {
        //String message;
        boolean isComplete = false;

        try (var fileReader = new Scanner(new File(filePath))) {

            while(fileReader.hasNextLine()) {
                String[] productInfo = fileReader.nextLine().split("\\|");
                var productToAdd = createProduct(productInfo);
                listOfProducts.put(productInfo[0], productToAdd);

            }
            //message = "Startup complete.";
            isComplete = true;
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.");
            //message = "File not found.";
        } catch (IllegalProductTypeException ipte) {
            System.out.println(ipte.getMessage());
        } catch (Exception e) {
            //message = e.getMessage();
        }
        //return message;
        return isComplete;
    }

    private static Product createProduct(String[] product) throws IllegalProductTypeException {
        Product createProduct;

        switch (product[3].trim()) {
            case TYPE_CANDY:
                createProduct =  new Candy(product[1], new BigDecimal(product[2]));
                break;
            case TYPE_CHIP:
                createProduct = new Chip(product[1], new BigDecimal(product[2]));
                break;
            case TYPE_DRINK:
                createProduct = new Drink(product[1], new BigDecimal(product[2]));
                break;
            case TYPE_GUM:
                createProduct = new Gum(product[1], new BigDecimal(product[2]));
                break;
            default:
                throw new IllegalProductTypeException("Product code not found.");
        }
        return createProduct;
    }

    /**
     * @param productCode key (String) with which the specified value is to be associated value.
     *                          Example - "A1" or "B3".
     * @return the value (Object – Product) to which the specified key is mapped, or null if this map
     *              contains no mapping for the key.
     */

    public static Product getProductByProductCode(String productCode) {

        Product product = null;
        try {
            var key = ProductCodeValidation(productCode);

            product = listOfProducts.get(key);

        } catch (IllegalProductCodeException ipce) {
            System.out.println(ipce.getMessage());
        } catch (Exception e) {/* System.out.println(e.getMessage()); */}
        return product;
    }

    /**
     * @param productCode key (String) with which the specified value is to be associated value.
     *                          Example - "A1" or "B3".
     * @return boolean – (true or false) indicates if the mapped value for the key (productCode)
            was successfully updated.
     */

    public static boolean updateProductQuantity(String productCode)  {
        //String message;
        boolean isSuccessful = false;

        try {
            var key = ProductCodeValidation(productCode);

            var productToUpdate = listOfProducts.get(key);
            var quantityToUpdate = productToUpdate.getQuantity();

            if(quantityToUpdate <= 0)
                throw new IllegalProductQuantityException("Product is SOLD OUT.");

            productToUpdate.setQuantity(productToUpdate.getQuantity() - 1);

            isSuccessful = true;
//            message = String.format("%s's quantity updated from %d to %d.",
//                      productToUpdate.getName(), quantityToUpdate, productToUpdate.getQuantity());

        }catch (IllegalProductCodeException ipce) {
            System.out.println(ipce.getMessage());
        } catch (Exception e) {/* message = e.getMessage(); */}
        //return message;
        return isSuccessful;
    }


    public static void totalSalesReport() {

        List<Product> products = new ArrayList<>(listOfProducts.values());

        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd-hh.mm.ssa"));
        String fileName = String.format("TOTAL_SALES_%s.csv", localDateTime);

        try (var printWriter = new PrintWriter(fileName)) {

            var iterator = products.iterator();

            while(iterator.hasNext()) {
                var product = iterator.next();
                printWriter.write(String.format("%s|%d\n", product.getName(), 5 - product.getQuantity()));
            }

            var productSales = products.stream().filter(p -> p.getQuantity() < 5).collect(Collectors.toList());

            var totalSales = productSales.stream().map(p ->
                new BigDecimal((5 - p.getQuantity())).multiply(p.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            printWriter.write(System.lineSeparator() + "**TOTAL SALES** $" + totalSales + System.lineSeparator());

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

    }

    public static void printListOfProducts() {
        for (Map.Entry<String, Product> product : listOfProducts.entrySet()) {
            System.out.printf("%S - %-19s  $%-6.2f %s\n",
                    product.getKey(), product.getValue().getName(), product.getValue().getPrice(),
                    product.getValue().getQuantity() > 0 ? product.getValue().getQuantity() : "SOLD OUT");
        }
    }

    private static String ProductCodeValidation(String productCode) throws IllegalProductCodeException {
        if(productCode == null)
            throw new IllegalProductCodeException("Product code was null");

        String key = productCode.toUpperCase().trim();
        var isValidCode = Arrays.asList(PRODUCT_CODES).contains(key);

        if (key.length() != 2 || !isValidCode)
            throw new IllegalProductCodeException("Product Code does not exist.");
        return key;
    }

    public static void clearProductList() {
        listOfProducts.clear();
    }

    public static Map<String,Product> getListOfProducts() {
        return listOfProducts;
    }

}
