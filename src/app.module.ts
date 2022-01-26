import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { LevelsModule } from './levels/levels.module';

@Module({
  imports: [LevelsModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
