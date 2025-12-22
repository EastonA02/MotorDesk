import express from "express";
import { protect } from "../middleware/authMiddleware.js";
import { createVehicle, getVehicles } from "../controllers/vehicleController.js";

const router = express.Router();

router.route("/")
  .post(protect, createVehicle)
  .get(protect, getVehicles);

export default router;
