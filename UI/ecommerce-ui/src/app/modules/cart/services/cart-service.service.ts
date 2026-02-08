import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export interface Order {
  id: number;
  userId: string;
  status: string;
  totalPrice: number;
}

@Injectable({
  providedIn: 'root',
})
export class CartServiceService {
  isCartCreated: boolean = false;

  constructor(private http: HttpClient) {}

  private baseUrlInvnetory = 'http://localhost:8080/api/inventory';
  private baseUrlOrder = 'http://localhost:8080/api/order-cart';

  addToCart(payload: any): any {
    return this.http.post(`${this.baseUrlOrder}/cart/add`, payload);
  }

  setCartCreated(isCreated: boolean) {
    this.isCartCreated = isCreated;
  }

  isCartPresent(): boolean {
    return this.isCartCreated;
  }
}
