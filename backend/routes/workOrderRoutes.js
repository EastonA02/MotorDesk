import express from "express";
import { protect } from "../middleware/authMiddleware.js";
import { createWorkOrder, getWorkOrders } from "../controllers/workOrderController.js";

const router = express.Router();

router.route("/")
  .post(protect, createWorkOrder)
  .get(protect, getWorkOrders);

export default router;
