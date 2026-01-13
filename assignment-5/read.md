All entities : 
   - Product
   - Category
   - Buyer
   - Seller
   - SellerProduct(Mapping table)
   - OrderItem(audits all kinds of product purchased)
   - Transaction(audits transactions made by buyers)

A single product can be sold by multiple vendors, so it makes sense to have the price tag associated to SellerProduct, **so that the price set by each vendor is tracked**.
A buyer can purchase multiple products of same type in a single transaction. So the orderItem can be used to track the type and quantity of product (vendor-specific) purchased by a customer. 
