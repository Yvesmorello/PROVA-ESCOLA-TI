import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VeiculoService } from '../service/veiculo-service';
import { Router } from '@angular/router';
import { Veiculo } from '../model/veiculo';
import { Page } from '../../model/page';

@Component({
  selector: 'veiculo-list',
  imports: [CommonModule],
  providers: [VeiculoService],
  templateUrl: './veiculo-list.html',
  styleUrl: './veiculo-list.css'
})
export class VeiculoList implements OnInit {

  response: Page<Veiculo> | null = null;

  constructor(
    private veiculoService: VeiculoService,
    private router: Router
  ) {}


  ngOnInit(): void {
    this.find();
  }

  goForm(id: number | null = null): void {
    id
      ? this.router.navigate(['/veiculo-form', id])
      : this.router.navigate(['/veiculo-form']);
  }

  find(): void {
    this.veiculoService.findAll()
      .subscribe(page => this.response = page);
  }

  delete(id: number) {
    this.veiculoService.delete(id)
      .subscribe(() => this.find());
  }


}
