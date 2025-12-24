import express from "express";
import { protect } from "../middleware/authMiddleware.js";
import { createWorkOrder, getWorkOrders, updateWorkOrder } from "../controllers/workOrderController.js";

const router = express.Router();

router.route("/")
  .post(protect, createWorkOrder)
  .get(protect, getWorkOrders);

router.route("/:id")
  .put(protect, updateWorkOrder);

export default router;
