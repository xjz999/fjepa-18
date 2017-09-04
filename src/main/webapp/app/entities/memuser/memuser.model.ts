import { BaseEntity } from './../../shared';

export class Memuser implements BaseEntity {
    constructor(
        public id?: number,
        public truename?: string,
        public enname?: string,
        public password?: string,
        public sex?: number,
        public email?: string,
        public mobile?: string,
        public memlevel?: number,
        public portrait?: string,
        public createtime?: any,
        public edittime?: any,
        public qqtoken?: string,
        public wechattoken?: string,
        public weibotoken?: string,
        public regtype?: number,
        public regphoto?: any,
    ) {
    }
}
