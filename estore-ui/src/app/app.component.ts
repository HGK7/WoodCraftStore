import { Component } from '@angular/core';
import { LoginComponent } from './login/login.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Wooden Furniture Store';
  public showNavBar = true;

  toggleNavBar(component: any) {
   if(component instanceof LoginComponent) {
      this.showNavBar = false;
   } else {
      this.showNavBar = true;
   }
}
}
