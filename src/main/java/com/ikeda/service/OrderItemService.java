package com.ikeda.service;

import java.util.List;
import java.util.stream.Collectors; // Stream API のために必要

import org.springframework.stereotype.Service;

import com.ikeda.entity.DvdItem;
import com.ikeda.entity.Member;
import com.ikeda.entity.Order;
import com.ikeda.entity.OrderItem;
import com.ikeda.presentation.form.OrderListForm; // Form クラスのインポート
import com.ikeda.repository.OrderItemRepository;

@Service
public class OrderItemService {

    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    // 戻り値を List<OrderListForm> に変更
    public List<OrderListForm> findAll() {
        
        // 1. 全ての OrderItemを取得
        // ★ Order/Member/DvdItem情報も取得するため、Eager Loadingの設定が推奨されます
        List<OrderItem> orderItems = repository.findAll();

        // 2. OrderItemからOrderListFormへの変換を行う
        return orderItems.stream()
                .map(item -> {
                    // 関連エンティティの取得
                    Order order = item.getOrder(); 
                    Member member = order.getMember();
                    DvdItem product = item.getProduct();
                    
                    // Formのインスタンス化とデータ設定
                    OrderListForm form = new OrderListForm();
                    
                    // 注文明細 (OrderItem) から
                    form.setId(item.getId());
                    form.setQuantity(item.getQuantity());

                    // 注文 (Order) から
                    form.setOrderDate(order.getOrderDate()); 

                    // 会員 (Member) から
                    form.setUserName(member.getLastName() + " " + member.getFirstName());
                    form.setUserEmail(member.getEmail());
                    form.setUserPhone(member.getPhone()); // Member Entity に phone がある前提
                    form.setUserAddress(member.getAddress()); // Member Entity に address がある前提

                    // 商品 (DvdItem) から
                    form.setProductName(product.getTitle());
                    
                    // ★ 期間やステータス情報がEntityに存在しないため、ここでは暫定値/仮の値を設定します
                    //    本来は OrderItem, Order, または別のEntityにこれらの情報が必要です。
                    form.setRentalStart(order.getOrderDate().toLocalDate().plusDays(2)); // 仮の期間開始日
                    form.setRentalEnd(order.getOrderDate().toLocalDate().plusDays(5)); // 仮の期間終了日
                    form.setDays(3); // 仮の日数
                    form.setStatus("renting"); // 仮のステータス

                    return form;
                })
                .collect(Collectors.toList());
    }
    /**
     * 注文明細をIDで削除する
     */
    public void deleteById(Long id) {
        // Repository の標準メソッドを呼び出す
        repository.deleteById(id);
    }
}