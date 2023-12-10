public enum ProductType {
    FOOD,
    DRINKS,
    SANITARY,
    MAKEUP,
    OTHERS;



    public ProductType getDescription() {
        for(ProductType type : ProductType.values()){
            return type;}
        return null;
    }
}

