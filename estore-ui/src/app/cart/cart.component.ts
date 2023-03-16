import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { CartItem } from '../cartItem';
import { LoginService } from '../login.service';
import { LoginComponent } from '../login/login.component';
import { Router } from '@angular/router';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  items: CartItem[] = [];
  messageService: any;

  constructor(private loginService: LoginService, private routing: Router, private cartService: CartService) { }
    

  getItems(): void {
    this.loginService.getAccount(LoginComponent.loggedIn.id)
    .subscribe(account => this.items = account.cart);
  }

  ngOnInit(): void {
    this.getItems()
  }

  checkOut(): void{
    if (this.items.length == 0){ return;}
    this.routing.navigate(["/checkout"])
}

  delete(cartItem: CartItem): void {

  this.loginService.getAccount(LoginComponent.loggedIn.id)
    .subscribe(account => this.items = account.cart);
    for (let i = 0; i< this.items.length; i++){
      if (this.items[i].id == cartItem.id && this.items[i].woodType == cartItem.woodType && this.items[i].quantity == cartItem.quantity){
        this.items.splice(i,1)
        break
      }
    }
    LoginComponent.loggedIn.cart = this.items
    this.loginService.updateAccount(LoginComponent.loggedIn).subscribe()

}

}
