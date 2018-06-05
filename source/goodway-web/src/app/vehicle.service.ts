import { AngularFireDatabase } from 'angularfire2/database';
import { Injectable } from '@angular/core';

@Injectable()
export class VehicleService {

  constructor(private db: AngularFireDatabase) { }

  create(vehicle) { 
    return this.db.list('/vehicles').push(vehicle);
  }

  getAll() {
    return this.db.list('/vehicles');
  }
  
  get(vehicleId) { 
    return this.db.object('/vehicles/' + vehicleId);
  }

  update(vehicleId, vehicle) { 
    return this.db.object('/vehicles/' + vehicleId).update(vehicle);
  }

  delete(vehicleId) { 
    return this.db.object('/vehicles/' + vehicleId).remove();
  }
}
