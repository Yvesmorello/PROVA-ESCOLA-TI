import { Routes } from '@angular/router';

export const routes: Routes = [

    {path: '', redirectTo: 'veiculo-list', pathMatch: 'full'},
    {path: 'veiculo-list', loadComponent: () => import('./veiculo/veiculo-list/veiculo-list').then(component => component.VeiculoList)},
    {path: 'veiculo-form', loadComponent: () => import('./veiculo/veiculo-form/veiculo-form').then(component => component.VeiculoForm)},
];
