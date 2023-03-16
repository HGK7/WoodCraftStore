import { Component, OnInit } from '@angular/core';
import { NavigationEnd } from '@angular/router';
import { Account } from '../account';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';
import { MessageService } from '../message.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  static loggedIn: any;

  constructor(private loginService: LoginService, private routing: Router,private messageService: MessageService) { }
  accounts: any
  temp:any
  ngOnInit(): void {
  }

  login(name: string): void{
    this.loginService.searchAccounts(name).subscribe(x => this.accounts = x as Account[] )
  

    if (this.accounts.length>0){
      if (this.accounts[0].name == "admin"){
        this.routing.navigate(["/admin-dashboard"])
        this.messageService.clear()
        LoginComponent.loggedIn = this.accounts[0];
        this.log("Logged in as admin")

      }
      else{
        LoginComponent.loggedIn = this.accounts[0];
        this.routing.navigate(["/dashboard"])
        this.messageService.clear()
        this.log(`Welcome ${LoginComponent.loggedIn.name}`)
      }
    } 
    else{
      name = name.trim();
      if (!name) { return; }
      this.loginService.addAccount({ name } as Account)
        .subscribe(account => {
        this.accounts.push(account);
        LoginComponent.loggedIn = this.accounts[0];
      });
      this.routing.navigate(["/dashboard"])
      this.messageService.clear()

    }
    }



  private log(message: string) {
      this.messageService.add(`LoginService: ${message}`);
  }

  
}
