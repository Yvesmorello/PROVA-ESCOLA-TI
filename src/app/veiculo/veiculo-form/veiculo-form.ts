import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { VeiculoService } from '../service/veiculo-service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Veiculo } from '../model/veiculo';

@Component({
  selector: 'veiculo-form',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './veiculo-form.html',
  styleUrl: './veiculo-form.css'
})
export class VeiculoForm {

  form: FormGroup<any> = new FormGroup('');
  id: number | null = null;

  constructor(
    private service: VeiculoService,
    private builder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {

    this.id = Number.parseInt(this.route.snapshot.paramMap.get('id') ?? '');

    if (!this.isValidId()) {
      return this.createForm();
    }

    this.createForm();
    this.populateForm(this.id);
  }

  createForm(): void {
    this.form = this.builder.group({
      id: [null],
      modelo: ['', Validators.required],
      anoFabricacao: ['', Validators.required],
      placa: [null, Validators.required],
      acessorios: ['', Validators.required], 
    });
  }

  populateForm(id: number): void {
    this.findById(id)
      .subscribe((veiculo: { [key: string]: any; }) => {
        this.form.patchValue(veiculo);
      });
  }

  findById(id: number): Observable<Veiculo> {
    return this.service.findById(id);
  }

  onSubmit(): void {

    const formValue = this.form.value;
    const veiculo: Veiculo = {
      ...formValue,
      acessorios: typeof formValue.acessorios === 'string'
        ? formValue.acessorios.split(',').map((a: string) => a.trim()).filter((a: string) => a)
        : []
    };

    this.isValidId()
      ? this.service.put(this.id!, veiculo)
          .subscribe(() => this.router.navigate(['/veiculo-list']))
      : this.service.create(veiculo)
          .subscribe(() => this.router.navigate(['/veiculo-list']));
  }

  isValidId() {
    return this.id ? !isNaN(this.id) : false;
  }

}
