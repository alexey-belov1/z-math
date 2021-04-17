export interface User {
  login: string;
  password: string;
  email?: string;
}

export interface AuthResponse {
  Authorization: string;
  Expires: string;
  Login: string;
}

export interface Subject {
  id: number;
  name: string;
}

export interface Task {
  id?: number;
  user: {
    id: number;
    login?: string;
    balance?: number;
    created?: Date;
    email?: string;
    password?: string;
    role?: {
      id: number;
      name: string;
    },
  };
  subject: {
    id: number;
    name?: string;
  };
  file: string;
  comment: string;
  deadline: Date;
  status?: {
    id: number;
    name?: string;
  };
  cost: number;
  created?: Date;
  contact?: string;
  cause?: string;
  hidden: boolean;
}

export interface Review {
  user: {
    id: number;
    login?: string;
  };
  created?: Date;
  text: string;
}
