import { ApiProperty } from '@nestjs/swagger';

export class NotificationResponseDto {
  @ApiProperty()
  id: string;

  @ApiProperty()
  microservice: string;

  @ApiProperty()
  action: string;

  @ApiProperty()
  entityId: string;

  @ApiProperty()
  entityType: string;

  @ApiProperty()
  createAt: Date;

  @ApiProperty()
  eventTimestamp: Date;

  @ApiProperty({ required: false })
  data?: Record<string, any>;

  @ApiProperty()
  read: boolean;

  @ApiProperty()
  severity?: string;

  @ApiProperty()
  processed: boolean;

  @ApiProperty()
  clientIp?: string;

  @ApiProperty()
  clientHost?: string;
}
