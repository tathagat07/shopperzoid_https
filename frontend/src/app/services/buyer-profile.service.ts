import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Buyer } from '../buyer-profile/buyer';

@Injectable({
  providedIn: 'root'
})
export class BuyerProfileService {

  private get_url="http://15.206.62.131:8080/buyerProfile/api/v1/buyer";
  private put_url="http://15.206.62.131:8080/buyerProfile/api/v1/buyer";


  constructor(private http: HttpClient) { }

  getBuyer(emailId): Observable<Buyer>{
    console.log(this.get_url+`/${emailId}`);
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    };
   return this.http.get<Buyer>(this.get_url+`/${emailId}`,httpOptions);

  }

  putBuyer(buyer): Observable<Buyer>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    };
    return this.http.put<Buyer>(this.put_url,buyer,httpOptions);
  }

  // postBuyer(buyer):Observable<Buyer>{
  //   return this.http.post<Buyer>(this.post_url,buyer);
  // }

}
