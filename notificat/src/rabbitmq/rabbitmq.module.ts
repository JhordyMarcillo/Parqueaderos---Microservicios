import { Module } from '@nestjs/common';
import { NotificationModule } from '../notifications/notification.module.js';
import { RabbitMQService } from './rabbitmq.service.js';

@Module({
  imports: [NotificationModule],
  providers: [RabbitMQService],
})
export class RabbitMQModule {}
