import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-admin-details',
  templateUrl: './admin-details.component.html',
  styleUrls: ['./admin-details.component.css']
})
export class AdminDetailsComponent implements OnInit {
  @Input() product?: Product;
  
  constructor(
    private route: ActivatedRoute,
    private ProductService: ProductService,
    private location: Location
  ) {}
  ngOnInit(): void {
    this.getProduct();
  }
  goBack(): void {
    this.location.back();
  }
  getProduct(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.ProductService.getProduct(id)
      .subscribe(product => this.product = product);
  }
  save(): void {
    if (this.product) {
      this.ProductService.updateProduct(this.product)
        .subscribe(() => this.goBack());
    }
  }
}

