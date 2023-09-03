import { Ingredient } from "../ingredients/ingredient";
import { Instruction } from "./instruction";

export interface Recipe {
    id: number;
    name: string;
    url: string;
    category: string;
    ingredients: Ingredient[];
    instructions: Instruction[];
    
}