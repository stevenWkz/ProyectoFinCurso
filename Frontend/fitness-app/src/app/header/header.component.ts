import { Component, OnInit } from '@angular/core';
import { AuthService } from '../usuarios/auth.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { ObjetivoService } from '../objetivos/objetivo.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router, private objetivoService: ObjetivoService) { }

  ngOnInit() {
  }

  
  public logout(): void {

    let username = this.authService.usuario.username;

    this.authService.logout();

    Swal.fire('Logout', `Has cerrado sesión con éxito ${username}`);

    this.router.navigate(['/login']);

  }

}
