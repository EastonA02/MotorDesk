import mongoose from "mongoose";

const notificationSchema = mongoose.Schema(
  {
    workOrder: {
      type: mongoose.Schema.Types.ObjectId,
          ref: "WorkOrder",
          required: true,
          index: true,
    },
    customer: {
      type: mongoose.Schema.Types.ObjectId,
          ref: "Customer",
          required: true,
    },
    message: {
      type: String,
      required: true,
      trim: true,
    },
    providerStatus: {
      type: String,
      enum: ["queued", "sent", "failed", "delivered"],
      default: "queued",
    },
  }, {timestamps: true},
);

const Notification = mongoose.model("Notification", notificationSchema);
export default Notification;