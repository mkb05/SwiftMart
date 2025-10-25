import { Component } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  name = '';
  email = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    const payload = {
      email: this.email,
      password: this.password,
    };
    this.authService.register(payload).subscribe({
      next: (res) => {
        alert('Registration successful!');
        this.router.navigate(['/auth/login']);
      },
      error: (err) => {
        console.error(err);
        alert('Registration failed!');
      },
    });
  }
}
