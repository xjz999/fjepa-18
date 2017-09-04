import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { MemuserComponent } from './memuser.component';
import { MemuserDetailComponent } from './memuser-detail.component';
import { MemuserPopupComponent } from './memuser-dialog.component';
import { MemuserDeletePopupComponent } from './memuser-delete-dialog.component';

@Injectable()
export class MemuserResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const memuserRoute: Routes = [
    {
        path: 'memuser',
        component: MemuserComponent,
        resolve: {
            'pagingParams': MemuserResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'fjepaApp.memuser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'memuser/:id',
        component: MemuserDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'fjepaApp.memuser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const memuserPopupRoute: Routes = [
    {
        path: 'memuser-new',
        component: MemuserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'fjepaApp.memuser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'memuser/:id/edit',
        component: MemuserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'fjepaApp.memuser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'memuser/:id/delete',
        component: MemuserDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'fjepaApp.memuser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
