import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { CartItem } from '../cartItem';
import { LoginService } from '../login.service';
import { LoginComponent } from '../login/login.component';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { CartComponent } from '../cart/cart.component';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  items: CartItem[] = [];
  total: number = 0;
  

  constructor(private location: Location, private loginService: LoginService, private routing: Router) { }


  getItems(): void {
    this.loginService.getAccount(LoginComponent.loggedIn.id)
    .subscribe(account => this.items = account.cart);
  }

  ngOnInit(): void {
    this.getItems()
    this.totalPrice()
  }

  goBack(): void {
    this.location.back();
  }

  totalPrice(): void{
      for (let i = 0; i < this.items.length; i++){
        this.total = this.total + this.items[i].price;
      }
    }

    Order(): void{
      this.items = []

      this.loginService.updateAccount({
        id: LoginComponent.loggedIn.id,
        name: LoginComponent.loggedIn.name,
        cart: this.items
      }).subscribe()


      this.routing.navigate(["/login"])

  }

}
  

