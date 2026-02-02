export interface NotificationEvent {
  id: string;
  microservice: string;
  action: string;
  entityId: string;
  entityType: string;
  message: string;
  timestamp: string;
  data?: Record<string, any>;
  severity?: string;
}
