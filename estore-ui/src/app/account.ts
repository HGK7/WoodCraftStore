import { CartItem } from "./cartItem";

export interface Account {
    id: number;
    name: string;
    cart: Array<CartItem>;
}