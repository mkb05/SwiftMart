import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { FormsModule } from '@angular/forms';
import { ProductsRoutingModule } from './products-routing.module';

@NgModule({
  declarations: [],
  imports: [CommonModule, FormsModule, ProductsRoutingModule],
})
export class ProductsModule {}
