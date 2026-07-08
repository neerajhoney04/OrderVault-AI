export interface Order {
  id: string;
  platform: string;
  orderId: string;
  productName: string;
  brand: string;
  seller: string;
  category: string;
  quantity: number;
  price: number;
  orderDate: string;
  deliveryDate?: string;
  returnDate?: string;
  status: 'pending' | 'delivered' | 'returned' | 'cancelled';
  imageUrl: string;
  notes?: string;
  createdAt: string;
}

export interface OrderFormData {
  platform: string;
  orderId: string;
  productName: string;
  brand: string;
  seller: string;
  category: string;
  quantity: number;
  price: number;
  orderDate: string;
  deliveryDate?: string;
  returnDate?: string;
  status: string;
  notes?: string;
}

export interface DashboardStats {
  totalOrders: number;
  totalSpent: number;
  pendingOrders: number;
  returnsDue: number;
  ordersByCategory: { category: string; count: number }[];
  monthlySpending: { month: string; amount: number }[];
}

export interface ApiResponse<T> {
  success: boolean;
  data?: T;
  error?: string;
  message?: string;
}

export interface ExtractedOrderData {
  platform: string;
  orderId: string;
  productName: string;
  brand: string;
  seller: string;
  category: string;
  quantity: number;
  price: number;
  orderDate: string;
  deliveryDate?: string;
  status: string;
}
