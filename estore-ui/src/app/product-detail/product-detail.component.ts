import { Component, OnInit,Input } from '@angular/core';
import { Product } from '../product';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { ProductService } from '../product.service';
import { LoginComponent } from '../login/login.component';
import { CartItem } from '../cartItem';
import { LoginService } from '../login.service';
import { Account } from '../account';
import { MessageService } from '../message.service';
import { CartComponent } from '../cart/cart.component';

@Component({  
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  @Input() product?: Product ;
  chosenWoodType!: string;
  chosenQuantity!: number;
  name!: string;
  price!: Number;

  
  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private location: Location,
    private loginService: LoginService,
    private messageService: MessageService
  ) {}
  ngOnInit(): void {
    this.getProduct();
  }
  goBack(): void {
    this.location.back();
  }
  getProduct(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.productService.getProduct(id)
      .subscribe(product => this.product = product);
  }
    /** Log a ProductService message with the MessageService */
    private log(message: string) {
      this.messageService.add(`ProductService: ${message}`);
  }
  addToCart(): void{
    if (this.product?.stock == 0){
      return;
    }
    let name = this.product?.name;
    let price = this.product?.price;
    if (this.product != undefined){
      if (this.chosenQuantity == 0){
        this.chosenQuantity = 1
      }
      if (this.product.stock - this.chosenQuantity >= 0)
        this.product.stock = this.product.stock - this.chosenQuantity    
      else if (this.product.stock - this.chosenQuantity < 0){
        this.chosenQuantity = this.product.stock
        this.product.stock = 0
      }
      
    let item: CartItem = {
      id: Number(this.route.snapshot.paramMap.get('id')),
      name: String(name),
      woodType:this.chosenWoodType,
      price: Number(price) *this.chosenQuantity,
      quantity: this.chosenQuantity
    }
    LoginComponent.loggedIn.cart.push(item);
    this.loginService.updateAccount(LoginComponent.loggedIn)
    .subscribe(newitem => {
      if (newitem.name != LoginComponent.loggedIn.name){LoginComponent.loggedIn.cart.push(newitem);}
    });
    
      this.productService.updateProduct({
        id: this.product.id,
        name: this.product.name,
        stock: this.product?.stock,
        woodType: this.product.woodType,
        price: this.product?.price
      }).subscribe(product => this.product = product);
    }
  }
}
