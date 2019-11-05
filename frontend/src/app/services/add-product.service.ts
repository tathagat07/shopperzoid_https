import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class SearchComp{
  productName: string;
}

@Injectable({
  providedIn: 'root'
})
export class AddProductService {

  searchComp:SearchComp={
    productName:''
  };
  constructor(private http:HttpClient) { }

  addProduct(product:any){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    };
    console.log(product);   
    console.log(httpOptions); 
    return this.http.post<any>('http://15.206.62.131:8080/product/api/v1/product',JSON.stringify(product),httpOptions);
  }

  addBook(book:any){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    };
    return this.http.post<any>('http://15.206.62.131:8080/product/api/v1/book',JSON.stringify(book),httpOptions);
  }

 searchProduct(productName):any{
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    })};
    console.log(httpOptions);
    
   return this.http.post<any>(`http://15.206.62.131:8080/product/api/v1/product/details?productName=${productName}`,httpOptions);
 } 

 updateProduct(product,seller){
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    })};
    console.log(seller);
    console.log(product);
    
    
    
   return this.http.put<any>(`http://15.206.62.131:8080/product/api/v1/product?productName=${product.productName}`,JSON.stringify(seller),httpOptions);
 }


}
