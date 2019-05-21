import { Objetivo } from '../objetivos/objetivo';
import { Alimento } from '../admin/alimento';

export class Usuario{

    id: number;
    nombre: string;
    apellido: string;
    email: string;
    direccion: string;
    username: string;
    password: string;
    roles: string[] = [];
    alimento: Alimento[] = [];
    objetivo: Objetivo;
}