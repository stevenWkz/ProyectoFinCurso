import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';

import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';


import { UsuariosComponent } from './usuarios/usuarios.component';
import { PerfilComponent } from './usuarios/perfil.component';
import { LoginComponent } from './usuarios/login.component';
import { RegistrarUserComponent } from './usuarios/registrar-user.component';
import { ObjetivosComponent } from './objetivos/objetivos.component';

import { FormsModule } from '@angular/forms';
import { AdminComponent } from './admin/admin.component';
import { ShowUsersComponent } from './admin/show-users.component';
import { ShowAlimentosComponent } from './admin/show-alimentos.component';
import { RegisterAlimentosComponent } from './admin/register-alimentos.component';
import { UpdateUsersComponent } from './admin/update-users.component';
import { UpdateAlimentosComponent } from './admin/update-alimentos.component';
import { UpdateObjetivoComponent } from './objetivos/update-objetivo/update-objetivo.component';






const routes: Routes = [

  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'perfil/:id', component: PerfilComponent},
  {path: 'objetivo', component: ObjetivosComponent},
  {path: 'objetivo/updateObjetivo/:id', component: UpdateObjetivoComponent},
  {path: 'register', component: RegistrarUserComponent},
  {path: 'showUsers', component: ShowUsersComponent},
  {path: 'showUsers/update/:id', component: UpdateUsersComponent},
  {path: 'showAlimentos', component: ShowAlimentosComponent},
  {path: 'showAlimentos/update/:id', component: UpdateAlimentosComponent},
  {path: 'registerAlimentos', component: RegisterAlimentosComponent},

 

];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    UsuariosComponent,
    PerfilComponent,
    LoginComponent,
    RegistrarUserComponent,
    ObjetivosComponent,
    AdminComponent,
    ShowUsersComponent,
    ShowAlimentosComponent,
    RegisterAlimentosComponent,
    UpdateUsersComponent,
    UpdateAlimentosComponent,
    UpdateObjetivoComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

