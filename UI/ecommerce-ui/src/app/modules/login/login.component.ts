import { Component } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  email = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    const payload = { email: this.email, password: this.password };
    this.authService.login(payload).subscribe({
      next: (res) => {
        this.authService.saveToken(res.token);
        alert('Login Successful');
        this.router.navigate(['/products']);
      },
      error: (err) => {
        console.error(err);
        alert('Invalid Credentials');
      },
    });
  }
}
