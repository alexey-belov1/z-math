import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {MainLayoutComponent} from "../layouts/main-layout/main-layout.component";


const routes: Routes = [
    {
        path: '', component: MainLayoutComponent, children: [
            {path: '', redirectTo: '/', pathMatch: 'full'},
            {
                path: '',
                loadChildren: () => import('./../entities/home-page/home-page.module').then(m => m.HomePageModule)
            },
            {
                path: 'contacts',
                loadChildren: () => import('./../entities/contacts-page/contacts-page.module').then(m => m.ContactsPageModule)
            },
            {
                path: 'new-task',
                loadChildren: () => import('./../entities/new-task-page/new-task-page.module').then(m => m.NewTaskPageModule)
            },
            {
                path: 'registration',
                loadChildren: () => import('./../entities/registration-page/registration-page.module').then(m => m.RegistrationPageModule)
            },
            {
                path: 'reviews',
                loadChildren: () => import('./../entities/reviews-page/reviews-page.module').then(m => m.ReviewsPageModule)
            },
            {
                path: 'rules',
                loadChildren: () => import('./../entities/rules-page/rules-page.module').then(m => m.RulesPageModule)
            },
            {
                path: 'tasks',
                loadChildren: () => import('./../entities/tasks-page/tasks-page.module').then(m => m.TasksPageModule)
            },
            {
                path: 'password-recovery',
                loadChildren: () => import('./../entities/password-recovery-page/password-recovery-page.module').then(m => m.PasswordRecoveryPageModule)
            },
            {
                path: 'no-authorization',
                loadChildren: () => import('./../entities/no-authorization-page/no-authorization-page.module').then(m => m.NoAuthorizationPageModule)
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {
        preloadingStrategy: PreloadAllModules
    })],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
