import express from "express";
import { protect } from "../middleware/authMiddleware.js";
import { authorize } from "../middleware/authorizeMiddleware.js";

import { createVehicle, getVehicles } from "../controllers/vehicleController.js";

const router = express.Router();

router.route("/")
  .post(protect, authorize("Admin", "Owner"), createVehicle)
  .get(protect, getVehicles);

export default router;
