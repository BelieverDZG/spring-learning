package com.dzg.thread.pattern;

import java.util.LinkedList;

public class ProducerConsumer {

    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                Message message = new Message(id, "产品" + id);
                queue.put(message);
                System.out.println("生产了：" + message.toString());
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Message take = queue.take();
                    System.out.println("消费了 :" + take.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者").start();
    }
}

//线程之间通信
class MessageQueue {
    //消息队列集合
    private LinkedList<Message> list = new LinkedList<>();
    //消息队列容量
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public Message take() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    //队列为空，消费者线程等待
                    System.out.println("队列为空，消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //从队列头部获取消息并返回
            Message message = list.removeFirst();
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message) {
        synchronized (list) {
            while (list.size() == capacity) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addLast(message);
            list.notifyAll();
        }
    }
}

class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }
}