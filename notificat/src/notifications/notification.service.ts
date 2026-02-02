import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CreateNotificationDto } from './dto/create-notification.dto.js';
import { Notification } from './entity/notification.entity.js';

@Injectable()
// eslint-disable prettier/prettier
export class NotificationService {
  constructor(
    @InjectRepository(Notification)
    private notificationRepository: Repository<Notification>,
  ) {}

  async create(dto: CreateNotificationDto): Promise<Notification> {
    const entityNotifi = this.notificationRepository.create({
      ...dto, //... -> crea una copia de dto y agrega nuevos valores
    });
    return await this.notificationRepository.save(entityNotifi);
  }

  async findAll(): Promise<Notification[]> {
    return await this.notificationRepository.find();
  }
}
