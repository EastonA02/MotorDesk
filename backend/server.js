import express from "express"
import dotenv from "dotenv"
import cors from "cors"
import connectDB from "./config/db.js"

import { errorHandler } from "./middleware/errorMiddleware.js";

import customerRoutes from "./routes/customerRoutes.js";
import vehicleRoutes from "./routes/vehicleRoutes.js";
import workOrderRoutes from "./routes/workOrderRoutes.js";
import userRoutes from "./routes/userRoutes.js";

dotenv.config(); //import values from .env file
connectDB();

const app = express()
app.use(cors()) //allow server to accept requests from different web domains
app.use(express.json());

app.use("/api/customers", customerRoutes);
app.use("/api/vehicles", vehicleRoutes);
app.use("/api/workOrders", workOrderRoutes);
app.use("/api/users", userRoutes);

//tells express if any middleware or controller throwns an error, send it here
app.use(errorHandler); //placed last becuase middleware runs from top to bottom, should catch errors from routes, if earlier would miss them

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port: ${PORT}`));