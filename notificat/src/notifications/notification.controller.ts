import { Controller, Get, Post, Req } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import express from 'express';
import { NotificationService } from './notification.service.js';

@Controller('notifications')
@ApiTags('notifications') //para swagger
export class NotificationsController {
  constructor(private readonly notificationService: NotificationService) {} //constante a nivel global

  @Post()
  create(@Req() request: express.Request) {
    const forwardedFor = request.headers['x-forwarded-for']; //obtiene la IP
    let clientIp: string | undefined;

    if (forwardedFor) {
      clientIp = forwardedFor.toString().split(',')[0];
    } else {
      clientIp = request.ip; //usa la IP directa
    }

    const clientHost = request.hostname; //obtiene el host

    console.log('IP:', clientIp);
    console.log('Host:', clientHost);

    return { clientIp, clientHost };
  }

  @Get()
  async findAll() {
    return await this.notificationService.findAll();
  }
}
