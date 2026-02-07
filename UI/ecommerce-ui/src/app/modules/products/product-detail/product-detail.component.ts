import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router, Route, ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';
import { NgIf } from '@angular/common';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-detail',
  imports: [],
  templateUrl: './product-detail.component.html',
  styleUrl: './product-detail.component.css',
})
export class ProductDetailComponent {
  product: any;
  isLoading = true;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private productService: ProductService,
  ) {}

  ngOnInit(): void {
    const productId = Number(this.route.snapshot.paramMap.get('id'));

    this.productService.getProductById(productId).subscribe({
      next: (data) => {
        this.product = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Failed to load products', err);
        this.isLoading = false;
      },
    });
  }
}
