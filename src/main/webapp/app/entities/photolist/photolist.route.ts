import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PhotolistComponent } from './photolist.component';
import { PhotolistDetailComponent } from './photolist-detail.component';
import { PhotolistPopupComponent } from './photolist-dialog.component';
import { PhotolistDeletePopupComponent } from './photolist-delete-dialog.component';

@Injectable()
export class PhotolistResolvePagingParams implements Resolve<any> {

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

export const photolistRoute: Routes = [
    {
        path: 'photolist',
        component: PhotolistComponent,
        resolve: {
            'pagingParams': PhotolistResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'fjepaApp.photolist.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'photolist/:id',
        component: PhotolistDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'fjepaApp.photolist.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const photolistPopupRoute: Routes = [
    {
        path: 'photolist-new',
        component: PhotolistPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'fjepaApp.photolist.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'photolist/:id/edit',
        component: PhotolistPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'fjepaApp.photolist.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'photolist/:id/delete',
        component: PhotolistDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'fjepaApp.photolist.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
