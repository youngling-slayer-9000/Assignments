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

Queries:
1)For a seller, find the total revenue generated within an interval
   
Select 
   sp.seller_id as Seller
   sum(oi.quantity * sp.price) as revenue
from 
   orderItem oi
   join 
   SellerProduct sp
on
  oi.sp_id = sp.sp_id
  join
  transaction t
on
  t.txn_id = oi.txn_id
where
  t.time >= '2026-01-01'
  and t.time <  '2026-02-01'
group by
   sp.seller_id



2)Top 10 selling products of the year

   select
      sp.product_id as  product,
      sum(oi.quantity * sp.price) as revenue
   from 
      orderItem oi
      join
      SellerProduct sp
   on
      oi.sp_id = sp.sp_id
   group by 
      sp.product_id      
   order by
      revenue



   3) Select
          c.category_id as category,
         sum(oi.quantity * oi.price) as revenue
      From
         product pr
         join
         orderItem oi
      on
         pr.product_id = oi.product_id
         join
         category c
      on
         c.category_id = pr.category_id
      group by
         c.category_id
      order by
         revenue
      
      
